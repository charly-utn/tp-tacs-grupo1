import { AxiosResponse } from "axios";
import { OrderRequest } from "../interfaces/OrderRequest";
import { instance } from "./BaseClient";


const endpoint = 'orders'

export const findOrders = async () => {
  try {
    const response = await instance.get("orders");
    return response.data;
  } catch (error) {
    console.log("Error findOrders")
    throw error;
  }
}

export const createOrder = async(order: OrderRequest): Promise<AxiosResponse<{orderId: string}, any>> => {
    return instance.post("orders", order);
}

export const addItem = async (orderId: string, itemId: string, quantity: number) => {
  return instance.post(`${endpoint}/${orderId}/items`, {id: itemId, quantity: quantity})
}

export const removeItem = async (orderId: string, itemId: string) => {
  return instance.delete(`${endpoint}/${orderId}/items/${itemId}`)
}

export const updateQuantity = async (orderId: string, itemId: string, quantity: number) => {
  return instance.patch(`${endpoint}/${orderId}/items/${itemId}`, {quantity})
}