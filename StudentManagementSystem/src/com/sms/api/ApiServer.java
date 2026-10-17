package com.sms.api;
//this is new

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiServer {

	private static final StudentDAO dao = new StudentDAO();

	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
		server.createContext("/api/students", new StudentHandler());
		server.setExecutor(null); // use the default executor
		server.start();
		System.out.println("API server running at http://localhost:8080/api/students");
	}

	static class StudentHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			// Let the frontend (a different port) call this server
			exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
			exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
			exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

			String method = exchange.getRequestMethod();

			// Browsers send OPTIONS first to check permissions (CORS preflight)
			if (method.equals("OPTIONS")) {
				exchange.sendResponseHeaders(204, -1);
				return;
			}

			String path = exchange.getRequestURI().getPath(); // /api/students  OR  /api/students/5
			String[] parts = path.split("/");                 // ["", "api", "students", "5"]

			Integer id = null;
			if (parts.length == 4) {
				try {
					id = Integer.parseInt(parts[3]);
				} catch (NumberFormatException e) {
					sendResponse(exchange, 400, "{\"error\":\"Invalid id\"}");
					return;
				}
			}

			try {
				if (method.equals("GET") && id == null) {
					handleGetAll(exchange);
				} else if (method.equals("GET")) {
					handleGetOne(exchange, id);
				} else if (method.equals("POST") && id == null) {
					handleCreate(exchange);
				} else if (method.equals("PUT") && id != null) {
					handleUpdate(exchange, id);
				} else if (method.equals("DELETE") && id != null) {
					handleDelete(exchange, id);
				} else {
					sendResponse(exchange, 405, "{\"error\":\"Method not allowed\"}");
				}
			} catch (Exception e) {
				e.printStackTrace();
				sendResponse(exchange, 500, "{\"error\":\"Server error\"}");
			}
		}

		private void handleGetAll(HttpExchange exchange) throws IOException {
			ArrayList<Student> students = dao.getAllStudents();
			sendResponse(exchange, 200, JsonUtil.toJsonArray(students));
		}

		private void handleGetOne(HttpExchange exchange, int id) throws IOException {
			Student student = dao.getStudentById(id);
			if (student == null) {
				sendResponse(exchange, 404, "{\"error\":\"Student not found\"}");
			} else {
				sendResponse(exchange, 200, JsonUtil.toJson(student));
			}
		}

		private void handleCreate(HttpExchange exchange) throws IOException {
			String body = readBody(exchange);
			Student student = JsonUtil.parseStudent(body);
			dao.addStudent(student);
			sendResponse(exchange, 201, "{\"message\":\"Student added\"}");
		}

		private void handleUpdate(HttpExchange exchange, int id) throws IOException {
			String body = readBody(exchange);
			Student student = JsonUtil.parseStudent(body);
			student.setId(id);
			boolean updated = dao.updateStudent(student);
			if (updated) {
				sendResponse(exchange, 200, "{\"message\":\"Student updated\"}");
			} else {
				sendResponse(exchange, 404, "{\"error\":\"Student not found\"}");
			}
		}

		private void handleDelete(HttpExchange exchange, int id) throws IOException {
			boolean deleted = dao.deleteStudent(id);
			if (deleted) {
				sendResponse(exchange, 200, "{\"message\":\"Student deleted\"}");
			} else {
				sendResponse(exchange, 404, "{\"error\":\"Student not found\"}");
			}
		}

		private String readBody(HttpExchange exchange) throws IOException {
			Scanner scanner = new Scanner(exchange.getRequestBody(), StandardCharsets.UTF_8).useDelimiter("\\A");
			return scanner.hasNext() ? scanner.next() : "";
		}

		private void sendResponse(HttpExchange exchange, int statusCode, String jsonResponse) throws IOException {
			exchange.getResponseHeaders().add("Content-Type", "application/json");
			byte[] bytes = jsonResponse.getBytes(StandardCharsets.UTF_8);
			exchange.sendResponseHeaders(statusCode, bytes.length);
			OutputStream os = exchange.getResponseBody();
			os.write(bytes);
			os.close();
		}
	}
}