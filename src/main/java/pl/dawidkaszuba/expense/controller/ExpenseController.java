package pl.dawidkaszuba.expense.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dawidkaszuba.expense.config.ExpenseServiceConfig;
import pl.dawidkaszuba.expense.entity.Expense;
import pl.dawidkaszuba.expense.model.Properties;
import pl.dawidkaszuba.expense.responseTemplate.ResponseTemplateUserWithExpenses;
import pl.dawidkaszuba.expense.service.ExpenseService;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseServiceConfig expenseServiceConfig;

    @PostMapping("/")
    public Expense saveExpense(@RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    @GetMapping("/{id}")
    public Expense getExpense(@PathVariable("id")  Long id) {
        return expenseService.findExpenseById(id);
    }

    @GetMapping("/user/{id}")
    @CircuitBreaker(name="userExpenses", fallbackMethod = "userExpensesFallBack")
    public ResponseTemplateUserWithExpenses getUserExpenses(@PathVariable("id") Long id) {
        return expenseService.findExpensesByUserId(id);
    }

    @GetMapping("/properties")
    public String getProperties() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(expenseServiceConfig.getMsg(), expenseServiceConfig.getBuildVersion());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }

    private ResponseTemplateUserWithExpenses userExpensesFallBack(Long id, Throwable t) {
       //just example, to refactor
        return new ResponseTemplateUserWithExpenses();
    }
}
