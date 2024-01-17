import axios from "axios"
import { API_BASE_URL } from "../../config/apiConfig"
import { REGISTER_REQUEST, REGISTER_SUCCESS,REGISTER_FAILURE,LOGIN_FAILURE,LOGIN_SUCCESS,LOGIN_REQUEST,GET_USER_FAILURE,GET_USER_SUCCESS,GET_USER_REQUEST, LOGOUT } from "./ActionType"

const token=localStorage.getItem("jwt");
const registerRequest= ()=>({type:REGISTER_REQUEST});
const registerSUCCESS= (user)=>({type:REGISTER_SUCCESS,payload:user});
const registerFAILURE= (error)=>({type:REGISTER_FAILURE,Payload:error});
export const register=(userData)=> async (dispatch)=>{
    dispatch(registerRequest())
    try{
        const response=await axios.post(`${API_BASE_URL}/auth/signup`,userData)
        const user=response.data;
        if(user.jwt){
            localStorage.setItem("jwt",user.jwt)
        }
        console.log("user ",user)
        dispatch(registerSUCCESS(user.jwt))
    }catch(error){
       dispatch(registerFAILURE(error.messge))     
    }
}

const loginRequest= ()=>({type:LOGIN_REQUEST});
const loginSUCCESS= (user)=>({type:LOGIN_SUCCESS,payload:user});
const loginFAILURE= (error)=>({type:LOGIN_FAILURE,Payload:error});

export const login=(userData)=> async (dispatch)=>{
    dispatch(loginRequest())
    try{
        const response=await axios.post(`${API_BASE_URL}/auth/signin`,userData)
        const user=response.data;
        if(user.jwt){
            localStorage.setItem("jwt",user.jwt)
        }
        console.log("user ",user)
        dispatch(loginSUCCESS(user.jwt))
    }catch(error){
       dispatch(loginFAILURE(error.messge))     
    }
}

const getUserRequest= ()=>({type:GET_USER_REQUEST});
const getUserSuccess= (user)=>({type:GET_USER_SUCCESS,payload:user});
const getUserFAILURE= (error)=>({type:GET_USER_FAILURE,Payload:error});

export const getUser=(jwt)=> async (dispatch)=>{
    dispatch(getUserRequest())
    try{
        const response=await axios.get(`${API_BASE_URL}/api/users/profile`,{
            headers:{
                "Authorization":`Bearer ${jwt}`
            }
        })
        const user=response.data;
        console.log("user ",user)
        
        dispatch(getUserSuccess(user))
    }catch(error){
       dispatch(getUserFAILURE(error.messge))     
    }
}

export const logout=()=>(dispatch)=>{
  dispatch({type:LOGOUT,payload:null})
  localStorage.clear();
}