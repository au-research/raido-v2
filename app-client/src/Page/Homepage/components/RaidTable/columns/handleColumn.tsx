import {GridColDef} from "@mui/x-data-grid";
import {NavLink} from "react-router-dom";
import {Button} from "@mui/material";

export const handleColumn: GridColDef = {
    field: "identifier",
    headerName: "Handle",
    width: 160,
    valueGetter: ({value}) => {
        const url = Array.isArray(value) ? value[0].id : value.id
        const urlSplit = url.substring(1).split("/");
        const prefix  = urlSplit[urlSplit.length - 2];
        const suffix  = urlSplit[urlSplit.length - 1];
        return `${prefix}/${suffix}`
    },
    renderCell: ({value}) => {
        return (
            <NavLink to={`/show-raid/${value}`}>
                <Button variant="outlined" size="small" fullWidth={true} sx={{textTransform:"none", minWidth:"150px"}}>{value}</Button>
            </NavLink>
        );
    },
}