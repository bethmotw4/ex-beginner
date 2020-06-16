package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserForm {
	@NotBlank(message = "名前を入力してください")
	private String name;
	@NotBlank(message = "年齢を入力してください")
	@Pattern(regexp = "^[0-9]*$", message = "数字を入力してください")
	private String age;
	@NotBlank(message = "コメントを入力してください")
	private String comment;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", comment=" + comment + "]";
	}
}
