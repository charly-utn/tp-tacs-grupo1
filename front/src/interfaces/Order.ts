import {Item} from "./Item";

export interface Order {
    id: string;
    name: string;
    items: Item[];
}