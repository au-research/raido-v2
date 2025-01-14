import { AppNavBarUnauthenticated } from "@/components/app-nav-bar-unauthenticated";
import { ProtectedRoute } from "@/components/protected-route";
import { AboutRaid } from "@/pages/about-raid";
import { ApiKey } from "@/pages/api-key";
import { Home } from "@/pages/home";
import { Login } from "@/pages/login";
import { Privacy } from "@/pages/privacy";
import { UsageTerms } from "@/pages/usage-terms";
import { Box } from "@mui/material";
import { RouteObject } from "react-router-dom";

export const otherRoutes: RouteObject[] = [
  {
    path: "/",
    element: <ProtectedRoute />,
    children: [
      {
        path: "",
        element: <Home />,
      },
    ],
  },
  {
    path: "/login",
    element: (
      <Box sx={{ pt: 5 }}>
        <AppNavBarUnauthenticated />
        <Login />
      </Box>
    ),
  },
  {
    path: "/privacy",
    element: (
      <>
        <AppNavBarUnauthenticated />
        <Privacy />
      </>
    ),
  },
  {
    path: "/terms",
    element: (
      <>
        <AppNavBarUnauthenticated />
        <UsageTerms />
      </>
    ),
  },
  {
    path: "/about-raid",
    element: (
      <>
        <AppNavBarUnauthenticated />
        <AboutRaid />
      </>
    ),
  },
  {
    path: "api-key",
    element: <ProtectedRoute />,
    children: [
      {
        path: "",
        element: <ApiKey />,
      },
    ],
  },
];
