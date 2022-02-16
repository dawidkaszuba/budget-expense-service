package pl.dawidkaszuba.expense.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dawidkaszuba.expense.model.User;

@FeignClient("user-service")
public interface UserFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "users/{userId}", consumes = "application/json ")
    User getUserDetails(  @PathVariable("userId") Long userId);
}
