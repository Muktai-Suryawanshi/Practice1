package com.example.Practice1.ServiceImpl;

import com.example.Practice1.DTO.UserDto;
import com.example.Practice1.Entity.User;
import com.example.Practice1.Repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    void testCreateUser(){
        UserDto userDto = new UserDto(null,"Mukta","Mukta@gmail.com","Admin");
        User user = new User(null,"Mukta","Mukta@gmail.com","Admin");
        User saved = new User(1L,"Mukta","Mukta@gmail.com","Admin");
        UserDto savedDto = new UserDto(1L,"Mukta","Mukta@gmail.com","Admin");

        when(modelMapper.map(userDto,User.class)).thenReturn(user);
        when(userRepo.save(user)).thenReturn(saved);
        when(modelMapper.map(saved,UserDto.class)).thenReturn(savedDto);

        UserDto result = userService.createUser(userDto);
        assertEquals(1L, result.getId());
    }


    @Test
    void testGetAllUsers(){

        User u1 = new User(1L,"A","a@gmail.com","USER");
        User u2 = new User(2L,"B","b@gmail.com","ADMIN");
        UserDto d1 = new UserDto(1L,"A","a@gmail.com","USER");
        UserDto d2 = new UserDto(2L,"B","b@gmail.com","ADMIN");

        when(userRepo.findAll()).thenReturn(List.of(u1,u2));
        when(modelMapper.map(u1,UserDto.class)).thenReturn(d1);
        when(modelMapper.map(u2,UserDto.class)).thenReturn(d2);

        List<UserDto> result = userService.getAllUsers();
        assertEquals(2,result.size());

    }

//    @Test
//    void testGetUsersByRole(){
//        User user = new User(1L,"RAJ","raj@gmail.com","USER");
//        UserDto dto = new UserDto(1L,"RAJ","raj@gmail.com","USER");
//
//        when(userRepo.findByRole("USER")).thenReturn(List.of(user));
//        when(modelMapper.map(user,UserDto.class)).thenReturn(dto);
//
//        List<UserDto> result = userService.getUsersByRole("USER");
//
//        assertEquals("USER",result.get(0).getRole());
//    }



}
