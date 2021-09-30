package pl.dawidkaszuba.expense.responseTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dawidkaszuba.expense.entity.Expense;
import pl.dawidkaszuba.expense.model.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateUserWithExpenses {
    private User user;
    private List<Expense> expenseList;
}
