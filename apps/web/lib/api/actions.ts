"use server";

import { API_URL } from "./constants";
import { Material } from "./model/material";
import { Product } from "./model/product";

export async function getProducts() {
  const res = await fetch(`${API_URL}/products`);

  if (!res.ok) throw new Error(`Failed: ${res.status}`);

  const products = (await res.json()) as Product[];
  return products.sort((a, b) => a.code.localeCompare(b.code));
}

export async function getMaterials() {
  const res = await fetch(`${API_URL}/raw-materials`);

  if (!res.ok) throw new Error(`Failed: ${res.status}`);

  const materials = (await res.json()) as Material[];
  return materials.sort((a, b) => a.code.localeCompare(b.code));
}
