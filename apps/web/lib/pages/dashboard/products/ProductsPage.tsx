import { getProducts } from "@/lib/api/actions";
import { Product } from "@/lib/api/model/product";
import Table from "@/lib/components/dashboard/Table";

const headerLabels: Record<keyof Omit<Product, "id">, string> = {
  code: "Código",
  name: "Nome",
  value: "Preço",
};

export default async function ProductsPage() {
  const products = await getProducts();

  return <Table table={products} headerLabels={headerLabels} />;
}
