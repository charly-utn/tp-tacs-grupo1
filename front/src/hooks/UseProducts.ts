import { useReducer } from "react"
import { ProductsReducer } from "../reducers/ProductsReducer"
import { findAll } from "../services/ProductsService"

const initialProducts: any = []

export const UseProducts = () => {

  const [products, dispatch] = useReducer(ProductsReducer, initialProducts)

  const getProducts = async(order_id: number) => {
    const allProducts = await findAll(order_id)

    dispatch({
      type: 'LOAD_PRODUCTS',
      payload: allProducts
    })
  }

  return {
    products,
    getProducts
  }
}
