import { getMaterials } from "@/lib/api/products/actions";
import Table from "@/lib/components/dashboard/Table";

export default async function MaterialsPage() {
  const materials = await getMaterials();

  return <Table table={materials} />;
}
