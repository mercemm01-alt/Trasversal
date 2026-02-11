package com.example.demo.services;

import com.example.demo.Model.LoginDTO;
import com.example.demo.Model.RespuestaLoginDTO;

public interface LoginService {

	RespuestaLoginDTO login(LoginDTO loginDTO);
}
