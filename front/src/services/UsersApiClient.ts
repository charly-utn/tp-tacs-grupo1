import {CreateUser} from "../interfaces/CreateUser";
import {Credentials} from "../interfaces/Credentials";
import {User} from "../interfaces/User";
import {instance} from "./BaseClient";

const endpoint = 'users'

export let user = {
  user: <User>{},
  onUserChanges: (user: User) => {}
}

export const getOrdersByUser = async () => {
  try {
    const response = await instance.get("orders");
    console.log("orders:", response.data);
    return response.data;
  } catch (error) {
    throw error;
  }
}
export const createUser = async(user: CreateUser) => {
  
  try {
    const response = await instance.post(endpoint, user);
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
    //user.user = response.data;
    //user.onUserChanges(user.user);    
    return response.data;
  } catch (error) {
    throw error;
  }
}

  export const logoutUser = () => {
  user.user = <User>{};
  user.onUserChanges(user.user);
  localStorage.removeItem('token');
}
