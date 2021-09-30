package pl.dawidkaszuba.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dawidkaszuba.expense.entity.Expense;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Expense findByExpenseId(Long expenseId);

    List<Expense> findExpensesByUserId(Long id);
}
