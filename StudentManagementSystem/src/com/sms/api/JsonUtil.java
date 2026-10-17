package com.sms.api;
//this is new

import com.sms.model.Student;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonUtil {

	// Convert one Student into a JSON object, e.g. {"id":1,"name":"Asif",...}
	public static String toJson(Student s) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"id\":").append(s.getId()).append(",");
		sb.append("\"name\":\"").append(escape(s.getName())).append("\",");
		sb.append("\"age\":").append(s.getAge()).append(",");
		sb.append("\"course\":\"").append(escape(s.getCourse())).append("\",");
		sb.append("\"email\":\"").append(escape(s.getEmail())).append("\"");
		sb.append("}");
		return sb.toString();
	}

	// Convert a list of Students into a JSON array, e.g. [{...},{...}]
	public static String toJsonArray(List<Student> students) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < students.size(); i++) {
			sb.append(toJson(students.get(i)));
			if (i < students.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	// Parse an incoming JSON object (from the frontend form) into a Student.
	// id is NOT read here — it comes from the URL instead (e.g. /api/students/5)
	public static Student parseStudent(String json) {
		String name = extractString(json, "name");
		int age = extractInt(json, "age");
		String course = extractString(json, "course");
		String email = extractString(json, "email");

		return new Student(name, age, course, email);
	}

	// ---------- helpers ----------

	private static String escape(String value) {
		if (value == null) return "";
		return value.replace("\\", "\\\\").replace("\"", "\\\"");
	}

	private static String extractString(String json, String key) {
		Pattern pattern = Pattern.compile("\"" + key + "\"\\s*:\\s*\"(.*?)\"");
		Matcher matcher = pattern.matcher(json);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return "";
	}

	private static int extractInt(String json, String key) {
		Pattern pattern = Pattern.compile("\"" + key + "\"\\s*:\\s*(\\d+)");
		Matcher matcher = pattern.matcher(json);
		if (matcher.find()) {
			return Integer.parseInt(matcher.group(1));
		}
		return 0;
	}
}