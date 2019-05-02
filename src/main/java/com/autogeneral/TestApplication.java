package com.autogeneral;

import com.autogeneral.model.ErrorMessage;
import com.autogeneral.model.ToDoItem;
import com.autogeneral.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class TestApplication
		implements CommandLineRunner {
	@Autowired
	private TestService testService;

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		testService.createOrUpdate(buildToDoItems("test bracket one"));
		testService.createOrUpdate(buildToDoItems("test bracket two ()"));
		testService.createOrUpdate(buildToDoItems("test bracket three {}"));
		testService.createOrUpdate(buildToDoItems("test bracket four ("));
		testService.createOrUpdate(buildToDoItems("test bracket five ]"));
		testService.list().forEach( toDoItem -> System.out.println(toDoItem));

	}

	private ToDoItem buildToDoItems(String todoItemText) {
		ToDoItem toDoItem = new ToDoItem();
		toDoItem.setText(todoItemText);
		return toDoItem;
	}


}
