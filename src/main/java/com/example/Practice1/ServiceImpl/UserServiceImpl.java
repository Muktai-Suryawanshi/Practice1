package com.example.Practice1.ServiceImpl;

import com.example.Practice1.DTO.ProductDto;
import com.example.Practice1.DTO.UserDto;
import com.example.Practice1.Entity.Product;
import com.example.Practice1.Entity.User;
import com.example.Practice1.Repository.ProductRepo;
import com.example.Practice1.Repository.UserRepo;
import com.example.Practice1.Service.ProductService;
import com.example.Practice1.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

        @Autowired
        private UserRepo userRepo;

        @Autowired
        private ModelMapper modelMapper;

        /**public UserServiceImpl(UserRepo userRepo,ModelMapper modelMapper){
            this.userRepo = userRepo;
            this.modelMapper = modelMapper;
        }**/


        @Override
        public UserDto createUser(UserDto userDto) {
            User user = this.modelMapper.map(userDto, User.class);
            User saved = this.userRepo.save(user);
            UserDto mapped = this.modelMapper.map(saved, UserDto.class);
            return mapped;
        }

        @Override
        public List<UserDto> getAllUsers() {
            List<UserDto> users = this.userRepo.findAll().stream()
                    .map(user -> modelMapper.map(user,UserDto.class))
                    .collect(Collectors.toList());
            return users;
        }

        @Override
        public List<UserDto> getUsersByRole(String role) {
            List<UserDto> collected = this.userRepo.findByRole(role).stream()
                    .map(user -> modelMapper.map(user, UserDto.class))
                    .collect(Collectors.toList());
            return collected;
        }


    }



