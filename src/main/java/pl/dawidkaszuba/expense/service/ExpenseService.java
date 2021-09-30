package pl.dawidkaszuba.expense.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.dawidkaszuba.expense.entity.Expense;
import pl.dawidkaszuba.expense.model.User;
import pl.dawidkaszuba.expense.repository.ExpenseRepository;
import pl.dawidkaszuba.expense.responseTemplate.ResponseTemplateUserWithExpenses;

import java.util.List;

@Service
@Slf4j
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private RestTemplate restTemplate;


    public Expense saveExpense(Expense expense) {
        log.info("Expense has been saved.");
        return expenseRepository.save(expense);
    }

    public Expense findExpenseById(Long expenseId) {
        return expenseRepository.findByExpenseId(expenseId);
    }

    public ResponseTemplateUserWithExpenses findExpensesByUserId(Long userId) {
        ResponseTemplateUserWithExpenses responseTemplateUserWithExpenses = new ResponseTemplateUserWithExpenses();
        List<Expense> expenseList = expenseRepository.findExpensesByUserId(userId);

        User user =
                restTemplate.getForObject("http://USER-SERVICE/users/" + userId,
                        User.class);

        responseTemplateUserWithExpenses.setUser(user);
        responseTemplateUserWithExpenses.setExpenseList(expenseList);

        return responseTemplateUserWithExpenses;
    }
}
