package lk.snt.dyeBackend.service;

import jakarta.transaction.Transactional;
import lk.snt.dyeBackend.dto.OrderDTO;
import lk.snt.dyeBackend.entity.Order;
import lk.snt.dyeBackend.repo.OrderRepository;
import lk.snt.dyeBackend.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Add new Order to the database
    public String saveOrder(OrderDTO orderDTO) {
        if (orderRepository.existsById(orderDTO.getOrderId())) {
            return VarList.RSP_DUPLICATED;
        } else {
            orderRepository.save(modelMapper.map(orderDTO, Order.class));
            return VarList.RSP_SUCCESS;
        }
    }

    // Update an existing order
    public String updateOrder(OrderDTO orderDTO) {
        if (orderRepository.existsById(orderDTO.getOrderId())) {
            orderRepository.save(modelMapper.map(orderDTO, Order.class));
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    // Load all Orders to the console
    public List<OrderDTO> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        return modelMapper.map(orderList, new TypeToken<ArrayList<OrderDTO>>(){}.getType());
    }

    // Search Order By orderId
    public OrderDTO searchOrderByOrderId(long orderId) {
        if (orderRepository.existsById(orderId)) {
            Order order = orderRepository.findById(orderId).orElse(null);
            return modelMapper.map(order, OrderDTO.class);
        } else {
            return null;
        }
    }

    // Search Order By some criteria (e.g., Order Name)
    // Add criteria based on your OrderDTO fields
    public OrderDTO searchOrderBySomeCriteria(String criteria) {
        // Implement this method based on your criteria
        return null;
    }

    // Delete Order By orderId
    public String deleteOrderByOrderId(long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
