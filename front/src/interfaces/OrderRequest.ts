import { ItemRequest } from "./ItemRequest";

export interface OrderRequest {
    items: ItemRequest[],
    name: string
}