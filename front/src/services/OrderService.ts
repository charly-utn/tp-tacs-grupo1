import { instance } from "./BaseClient"

const endpoint = 'orders'

export const addItem = async (orderId: string, itemId: string, quantity: number) => {
    return instance.post(`${endpoint}/${orderId}/items`, {id: itemId, quantity: quantity})
}

export const removeItem = async (orderId: string, itemId: string) => {
    return instance.delete(`${endpoint}/${orderId}/items/${itemId}`)
}

export const updateQuantity = async (orderId: string, itemId: string, quantity: number) => {
    return instance.patch(`${endpoint}/${orderId}/items/${itemId}`, {quantity})
}
  