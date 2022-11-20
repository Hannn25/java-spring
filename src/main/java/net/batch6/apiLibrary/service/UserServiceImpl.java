package net.batch6.apiLibrary.service;

import net.batch6.apiLibrary.model.dto.ResponseData;
import net.batch6.apiLibrary.model.dto.UserDto;
import net.batch6.apiLibrary.model.entity.DetailUser;
import net.batch6.apiLibrary.model.entity.User;
import net.batch6.apiLibrary.repository.DetailUserRepository;
import net.batch6.apiLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;



@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DetailUserRepository detailUserRepository;

    private ResponseData<Object> responseData;
    private User user;
    private DetailUser detailUser;
    private Map<Object, Object> data;

    @Override
    public ResponseData<Object> register(UserDto request) {
        // TODO Auto-generated method stub
        // check user apakah usernya ini udh terdaftar atau belum, check emailnya
        // select * from users where email = ?
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isPresent()) {
            responseData = new ResponseData<Object>(HttpStatus.BAD_REQUEST.value(), "User is found, please login!", null);
        } else {
            user = new User(request.getEmail(), request.getPassword());
            userRepository.save(user);
            detailUser = new DetailUser(request.getFirstName(), request.getLastName(), request.getPhoneNumber());
            detailUser.setUserId(user);
            detailUser.setUserEmail(user);
            detailUserRepository.save(detailUser);
            data = new HashMap<>();
            data.put("email", user.getEmail());
            data.put("firstName", detailUser.getFirstName());
            data.put("lastName", detailUser.getLastName());
            data.put("phoneNumber", detailUser.getPhoneNumber());

            // response
            responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "Success registerd", data);
        }
        return responseData;
    }

    @Override
    public ResponseData<Object> login(UserDto request) {
        // TODO Auto-generated method stub
        // check user
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isPresent()) {
            user = userOpt.get();

            if (request.getPassword().equals(user.getPassword())) {
                // data spesific
                data = new HashMap<>();
                data.put("email", user.getEmail());

                // response data
                responseData = new ResponseData<Object>(HttpStatus.OK.value(), "Success login.", data);
            } else {
                responseData = new ResponseData<Object>(HttpStatus.BAD_REQUEST.value(), "Wrong password", null);
            }

        } else {
            responseData = new ResponseData<Object>(HttpStatus.BAD_REQUEST.value(), "User is not found, please register",
                    null);
        }
        return responseData;
    }

}