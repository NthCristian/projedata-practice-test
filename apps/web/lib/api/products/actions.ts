"use server";

import { API_URL } from "../constants";
import { Material } from "../model/material";
import { Product } from "../model/product";

export async function getProducts() {
  return (await (await fetch(`${API_URL}/products`)).json()) as Product[];
}

export async function getMaterials() {
  return (await (await fetch(`${API_URL}/raw-materials`)).json()) as Material[];
}
