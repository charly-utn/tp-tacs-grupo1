import { instance } from "./BaseClient";

export const findAll = async (orderId = null) => {
  const params = {
    order_id: orderId
  }

  try {
    const response = await instance.get("items", {params})
    return response.data;
  } catch (error) {
    throw error;
  }
}
