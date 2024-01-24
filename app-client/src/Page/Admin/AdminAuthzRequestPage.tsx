import {TextSpan} from "Component/TextSpan";
import React from "react";
import {useQuery} from "@tanstack/react-query";
import {useAuthApi} from "Api/AuthApi";
import {CompactErrorPanel} from "Error/CompactErrorPanel";
import {
    Button,
    Card,
    CardContent,
    CardHeader,
    Container,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow
} from "@mui/material";
import {RefreshIconButton} from "Component/RefreshIconButton";
import {NavLink} from "react-router-dom";


export function AdminAuthzRequestPage() {
    const api = useAuthApi();
    const query = useQuery(['listAuthzRequest'],
        async () => await api.admin.listAuthzRequest());

    if (query.error) {
        return <CompactErrorPanel error={query.error}/>
    }

    if (query.isLoading) {
        return <TextSpan>loading...</TextSpan>
    }

    if (!query.data) {
        console.log("unexpected state", query);
        return <TextSpan>unexpected state</TextSpan>
    }
    return (
        <Container>
            <Card>
                <CardHeader
                    title="Authorisation requests"
                    action={
                        <RefreshIconButton
                            refreshing={query.isLoading}
                            onClick={() => query.refetch()}
                        />
                    }/>
                <CardContent>
                    <TableContainer>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell>Service point</TableCell>
                                    <TableCell>Identity</TableCell>
                                    <TableCell>ID Provider</TableCell>
                                    <TableCell>Requested</TableCell>
                                    <TableCell>Status</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {query.data.map((row) => (
                                    <TableRow
                                        key={row.id}
                                        // don't render a border under last row
                                        sx={{'&:last-child td, &:last-child th': {border: 0}}}
                                    >
                                        <TableCell component="th" scope="row">
                                            {row.servicePointName}
                                        </TableCell>
                                        <TableCell>{row.email}</TableCell>
                                        <TableCell>{row.idProvider}</TableCell>
                                        <TableCell>
                                            {
                                                Intl.DateTimeFormat("en-AU", {
                                                    dateStyle: "medium",
                                                    timeStyle: "short",
                                                    hour12: false,
                                                }).format(row.dateRequested)
                                            }
                                        </TableCell>
                                        <TableCell>
                                            <NavLink to={`/authz-respond?authzRequestId=${row.id}`}>
                                                <Button variant="text" sx={{textTransform:"none"}}>
                                                    {row.status}
                                                </Button>
                                            </NavLink>
                                        </TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>

                    </TableContainer>
                </CardContent>
            </Card>
        </Container>
    )
}