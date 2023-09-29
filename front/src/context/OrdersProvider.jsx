import { UseProducts } from '../hooks/UseProducts'
import { OrdersContext } from './OrdersContext'

export const OrdersProvider = ({children}) => {

    const {products, getProducts} = UseProducts()

    return (
        <OrdersContext.Provider value={
            {
                products,
                getProducts,
            }
        }>
            {children}
        </OrdersContext.Provider>
    )
}
