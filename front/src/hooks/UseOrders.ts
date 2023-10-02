import { useReducer } from "react"
import { OrdersReducer } from "../reducers/OrdersReducer"
import { createOrder, findOrders } from "../services/OrdersService"
import { Order } from "../interfaces/Order"
import { OrderRequest } from "../interfaces/OrderRequest"
import { AlertError, AlertOk } from "../components/SweetAlert"

const initialOrders: Order[] = []
/*[{
  id: '',
  name: '',
  items: []
}]*/

export const UseOrders = () => {

  const [orders, dispatch] = useReducer(OrdersReducer, initialOrders)

  const getOrders = async() => {
    const response = await findOrders()
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
    createOrder(order)
      .then(response => {
        dispatch({
          type: 'UPDATE_ORDERS',
          payload: response.data.orderId
        });
        AlertOk('Pedido', 'El pedido se creó correctamente');
      })
      .catch(e => AlertError('Pedido', 'Ocurrió un error al crear el pedido', e));

  }

  return {
    orders,
    getOrders,
    handleCreateOrder
  }
}
