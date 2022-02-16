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


    private final ExpenseService expenseService;
    private final ExpenseServiceConfig expenseServiceConfig;

    @Autowired
    public ExpenseController(ExpenseService expenseService, ExpenseServiceConfig expenseServiceConfig) {
        this.expenseService = expenseService;
        this.expenseServiceConfig = expenseServiceConfig;
    }

    @PostMapping("/")
    public Expense saveExpense(@RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    @GetMapping("/{id}")
    public Expense getExpense(@PathVariable("id")  Long id) {
        return expenseService.findExpenseById(id);
    }

    @GetMapping("/user/{id}")
    @CircuitBreaker(name="userExpenses")
    public ResponseTemplateUserWithExpenses getUserExpenses(@PathVariable("id") Long id) {
        return expenseService.findExpensesByUserId(id);
    }

    @GetMapping("/properties")
    private String getProperties() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(expenseServiceConfig.getMsg(), expenseServiceConfig.getBuildVersion());
        return ow.writeValueAsString(properties);
    }

    private ResponseTemplateUserWithExpenses userExpensesFallBack(Long id, Throwable t) {
       //just example, to refactor
        return new ResponseTemplateUserWithExpenses();
    }
}
