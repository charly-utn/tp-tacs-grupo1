import {Item} from "./Item";

export interface Order {
    id: number;
    name: string;
    items: Item[];
}