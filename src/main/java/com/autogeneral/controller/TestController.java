package com.autogeneral.controller;

import com.autogeneral.model.*;
import com.autogeneral.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class TestController {
    static final Pattern BRACKET_PATTERNS = Pattern.compile(".*\\{.*}|.*\\[.*\\]|.*\\(.*\\)");
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/tasks/validateBrackets", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getValidateBrackets(@RequestParam String input) {
        Matcher bracketMatcher = BRACKET_PATTERNS.matcher(input);
        ToDoItem toDoItem = new ToDoItem();
        boolean isBalance = bracketMatcher.matches();
        toDoItem.setText(input);
        toDoItem.setCompleted(true);
        toDoItem.setCreateAt(ZonedDateTime.now().toString());
        try {
            testService.createOrUpdate(toDoItem);
            BalanceTestResult balanceTestResult = new BalanceTestResult();
            balanceTestResult.setBalance(isBalance);
            balanceTestResult.setInput(input);
            testService.list().forEach(item -> System.out.println(item));
            return new ResponseEntity(balanceTestResult, HttpStatus.OK);
        } catch (Exception ee) {
            ee.printStackTrace();
            return new ResponseEntity(buildBadRequestDetails(input, ee), HttpStatus.BAD_REQUEST);
        }

    }

    private ToDoItemValidationError buildBadRequestDetails(@RequestParam String input, Exception ee) {
        ToDoItemValidationError toDoItemValidationError = new ToDoItemValidationError();
        ErrorMessageDetails details = new ErrorMessageDetails();
        details.setLocation("params");
        details.setParam("text");
        details.setMsg(ee.getMessage());
        details.setValue(input);
        toDoItemValidationError.setDetails(details);
        return toDoItemValidationError;
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createToDoItem(@RequestParam String text, @RequestParam (required = false) Boolean isCompleted) {
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setText(text);
        toDoItem.setCompleted(isCompleted != null ? isCompleted : false );
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ssZ");
        toDoItem.setCreateAt(formatter.format(new Date()));
        try {
            testService.createOrUpdate(toDoItem);
            return new ResponseEntity(toDoItem, HttpStatus.OK);
        } catch (Exception ee) {
            ee.printStackTrace();
            return new ResponseEntity(buildBadRequestDetails(text, ee), HttpStatus.BAD_REQUEST);

        }

    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getToDoItem(@PathVariable int id) {
        Optional<ToDoItem> toDoItem = testService.find(id);
        if (toDoItem.isPresent())
            return new ResponseEntity(toDoItem, HttpStatus.OK);
        else {
            ToDoItemNotFoundError toDoItemNotFoundError = new ToDoItemNotFoundError();
            toDoItemNotFoundError.setDetails(Arrays.asList(new ErrorMessage("Item not found")));
            return new ResponseEntity(toDoItemNotFoundError, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity updateTodoItem(@PathVariable int id, ToDoItem toDoItem) {
        Optional<ToDoItem> lookupItem = testService.find(id);
        if (lookupItem.isPresent()) {
            ToDoItem updateTodoItem = lookupItem.get();
            updateTodoItem.setText(toDoItem.getText());
            updateTodoItem.setCompleted(toDoItem.isCompleted());
            testService.createOrUpdate(updateTodoItem);
            return new ResponseEntity(updateTodoItem, HttpStatus.OK);
        } else {
            ToDoItemNotFoundError toDoItemNotFoundError = new ToDoItemNotFoundError();
            toDoItemNotFoundError.setDetails(Arrays.asList(new ErrorMessage("Item not found")));
            return new ResponseEntity(toDoItemNotFoundError, HttpStatus.NOT_FOUND);
        }
    }

}
