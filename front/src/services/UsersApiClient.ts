import { CreateUser } from "../interfaces/CreateUser";
import { Credentials } from "../interfaces/Credentials";
import { User } from "../interfaces/User";
import { instance } from "./BaseClient";

const endpoint = 'users'


  export const createUser = async(user: CreateUser) => {
    
    try {
      const response = await instance.post(endpoint, user)
      console.log("creado:", response.data);
      return response.data;
    } catch (error) {
        console.log("hubo un error:", error);
      throw error;
    }
  }

  export const loginUser = async(credentials: Credentials): Promise<User> => {
    try {
      const response = await instance.post(`${endpoint}/login`, credentials);
      return response.data;
    } catch (error) {
      throw error;
    }
  }