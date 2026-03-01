import { getMaterials } from "@/lib/api/actions";
import { Material } from "@/lib/api/model/material";
import Table from "@/lib/components/dashboard/Table";

const headerLabels: Record<keyof Omit<Material, "id">, string> = {
  code: "Código",
  name: "Nome",
  stockQuantity: "Estoque",
  unitOfMeasure: "Unidade de Medida",
};

export default async function MaterialsPage() {
  const materials = await getMaterials();

  return <Table table={materials} headerLabels={headerLabels} />;
}
