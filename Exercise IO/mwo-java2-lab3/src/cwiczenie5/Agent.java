package cwiczenie5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Agent implements Serializable {

	private String name;
	private String surname;
	private String number;
	private int age;
	private List<Task> tasks = new ArrayList<Task>();

	public Agent(String name, String surname, int age, String number) {
		super();
		this.name = name;
		this.surname = surname;
		this.number = number;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getNumber() {
		return number;
	}

	public int getAge() {
		return age;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void addTask(Task task) {
		tasks.add(task);
	}

	public void removeTask(Task task) {
		tasks.remove(task);
	}

}
