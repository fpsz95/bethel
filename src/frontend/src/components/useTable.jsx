import React from "react";
import { Table, TableCell, TableHead, TableRow } from "@material-ui/core";

export default function useTable(records, headCells) {
  // const pages =[5,10,25]
  // const [page, setPage] = useState(0);
  // const [rowsPerPage, setRowsPerP]
  const TblContainer = (props) => <Table>{props.children}</Table>;

  const TblHead = (props) => {
    return (
      <TableHead>
        <TableRow>
          {headCells.map((headCell) => (
            <TableCell key={headCell.id}>{headCell.label}</TableCell>
          ))}
        </TableRow>
      </TableHead>
    );
  };

  return {
    TblContainer,
    TblHead,
  };
}
