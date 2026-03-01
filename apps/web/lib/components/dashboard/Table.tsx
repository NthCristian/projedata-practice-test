export default function Table({
  table,
  headerLabels,
}: {
  table: Array<unknown>;
  headerLabels?: Record<string, string>;
}) {
  const keys = [
    ...new Set(table.flatMap((obj) => Object.keys(obj as object))),
  ].filter((k) => k !== "id");

  return (
    <table className="outline border-collapse [&_th,&_td]:py-3 [&_th,&_td]:px-8 **:whitespace-nowrap p-6 rounded-md w-full table-fixed">
      <thead>
        <tr className="border-b ">
          {keys.map((key) => (
            <th key={key}>{headerLabels?.[key] ?? key}</th>
          ))}
        </tr>
      </thead>
      <tbody>
        {table.map((obj, i) => (
          <tr key={i}>
            {keys.map((key) => (
              <td key={key} className="text-center">
                {(obj as Record<string, unknown>)[key] as string}
              </td>
            ))}
          </tr>
        ))}
      </tbody>
    </table>
  );
}
