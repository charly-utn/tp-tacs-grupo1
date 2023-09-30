import { OrderRequest } from "../interfaces/OrderRequest";
import { instance } from "./BaseClient";

export const findAll = async () => {
  try {
    const response = await instance.get("orders");
    return response.data;
  } catch (error) {
    console.log("Error findAll")
    throw error;
  }
}

export const createOrder = async(order: OrderRequest) => {
  try {
    const response = await instance.post("orders", order);
    return response.data;
  } catch (error) {
    console.log("Error createOrder")
    throw error;
  }
}