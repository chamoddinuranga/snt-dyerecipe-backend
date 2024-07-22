package lk.snt.dyeBackend.service;

import jakarta.transaction.Transactional;
import lk.snt.dyeBackend.dto.UserDTO;
import lk.snt.dyeBackend.entity.User;
import lk.snt.dyeBackend.repo.UserRepository;
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
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Add new Employee to the database
    public String saveUser(UserDTO userDTO) {

        if (userRepository.existsById(userDTO.getUserId())) {
            return VarList.RSP_DUPLICATED;
        }else if(userRepository.existsByUserName(userDTO.getUserName())){
            return VarList.RSP_DUPLICATED;
        }else {
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.RSP_SUCCESS;
        }
    }

   // update an existing user

    public String updateUser(UserDTO userDTO) {
        if (userRepository.existsById(userDTO.getUserId())){
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;

        }
    }
    // Load all Users to the console
    public List<UserDTO> getAllUsers() {
       List<User> userList = userRepository.findAll();
        return modelMapper.map(userList, new TypeToken<ArrayList<UserDTO>>(){

        }.getType());
    }

    // Search User By userId
    public UserDTO searchUserByUserId(long userId) {
        if (userRepository.existsById(userId)) {
            User employee = userRepository.findById(userId).orElse(null);
            return modelMapper.map(employee, UserDTO.class);
        }else {
            return null;
        }
    }

    // Search User By userName
    public UserDTO searchUserByUserName(String userName) {
        if (userRepository.existsByUserName(userName)){
            Optional<User> userOpt = userRepository.findByUserName(userName);
            return userOpt.map(user -> modelMapper.map(user, UserDTO.class)).orElse(null);
        }else {
            return null;
        }
    }

    // Delete User
    public String deleteUserByUserName(String userName) {
        if (userRepository.existsByUserName(userName)) {
            userRepository.deleteByUserName(userName);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}

