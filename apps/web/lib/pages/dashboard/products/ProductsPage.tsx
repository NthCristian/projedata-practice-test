import { getProducts } from "@/lib/api/products/actions";
import Table from "@/lib/components/dashboard/Table";

export default async function ProductsPage() {
  const products = await getProducts();

  return <Table table={products} />;
}
