import {
  ContactSupport as ContactSupportIcon,
  Home as HomeIcon,
} from "@mui/icons-material";
import {
  Alert,
  Button,
  Card,
  CardActions,
  CardContent,
  CardHeader,
  Container,
} from "@mui/material";
import { memo } from "react";
import { useNavigate } from "react-router-dom";

export const ErrorAlertComponent = memo(
  ({
    error,
    showButtons,
  }: {
    error?: Error | string;
    showButtons?: boolean;
  }) => {
    const navigate = useNavigate();
    return (
      <Container>
        <Card
          sx={{
            borderLeft: "solid",
            borderLeftColor: "error.main",
            borderLeftWidth: 3,
          }}
        >
          <CardHeader title="Error" subheader="An error occured" />
          <CardContent>
            <Alert severity="error">
              {(error && JSON.stringify(error, null, 2)) ||
                "Something went wrong."}
            </Alert>
          </CardContent>
          {showButtons && (
            <CardActions>
              <Button
                color="error"
                size="small"
                variant="outlined"
                onClick={() => navigate("/home")}
                startIcon={<HomeIcon />}
              >
                Back to home
              </Button>
              <Button
                color="error"
                size="small"
                variant="outlined"
                onClick={() =>
                  (location.href = `mailto:${
                    import.meta.env.VITE_SUPPORT_EMAIL || "contact@raid.org"
                  }`)
                }
                startIcon={<ContactSupportIcon />}
              >
                Contact Support
              </Button>
            </CardActions>
          )}
        </Card>
      </Container>
    );
  }
);

ErrorAlertComponent.displayName = "ErrorAlertComponent";
