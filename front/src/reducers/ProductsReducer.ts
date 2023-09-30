export const ProductsReducer = (state = [], action: any) => {
    switch (action.type) {
        case 'LOAD_PRODUCTS':
            return action.payload
        default:
            break;
    }
}
