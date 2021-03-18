package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.data.OrderRepository;
import tacos.domain.Order;
import tacos.domain.User;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    private OrderRepository orderRepository;
    private OrderProps props;

    public OrderController(OrderRepository orderRepository, OrderProps props) {
        this.orderRepository = orderRepository;
        this.props = props;
    }

    @GetMapping
    public String orderForUser(@AuthenticationPrincipal User user,
                               Model model) {
        Pageable pageable = PageRequest.of(0, props.getPageSize());
        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));

        return "orderList";
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user,
                            @ModelAttribute Order order) {
        if (order.getDeliveryName() == null)
            order.setDeliveryName(user.getFullname());

        if (order.getDeliveryStreet() == null)
            order.setDeliveryStreet(user.getStreet());

        if (order.getDeliveryCity() == null)
            order.setDeliveryCity(user.getCity());

        if (order.getDeliveryState() == null)
            order.setDeliveryState(user.getState());

        if (order.getDeliveryZip() == null)
            order.setDeliveryZip(user.getZip());

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors())
            return "orderForm";

        log.info("order : {} ", order);

        order.setUser(user);

        orderRepository.save(order);
        sessionStatus.setComplete();

       return "redirect:/";
    }
}
