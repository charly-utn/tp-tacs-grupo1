import { useReducer } from "react"
import { OrdersReducer } from "../reducers/OrdersReducer"
import { createOrder, findAll } from "../services/OrdersService"
import { Order } from "../interfaces/Order"
import { OrderRequest } from "../interfaces/OrderRequest"

const initialOrders: Order[] = []
/*[{
  id: '',
  name: '',
  items: []
}]*/

export const UseOrders = () => {

  const [orders, dispatch] = useReducer(OrdersReducer, initialOrders)

  const getOrders = async() => {
    const response = await findAll()
    const allOrders: Order[] = response.ordersIds.map((id: string) => <Order>{
      id: id,
      name: '',
      items: []
    })

    dispatch({
      type: 'LOAD_ORDERS',
      payload: allOrders
    })
  }

  const handleCreateOrder = async(order: OrderRequest) => {
    const response = await createOrder(order)

    dispatch({
      type: 'UPDATE_ORDERS', //ADD_ORDER??
      payload: response.orderId
    })
  }

  return {
    orders,
    getOrders,
    handleCreateOrder
  }
}
