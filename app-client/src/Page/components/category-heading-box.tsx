import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";

export default function CategoryHeadingBox({
  title,
  subtitle,
  IconComponent,
}: {
  title: string;
  subtitle: string;
  IconComponent: any;
}) {
  return (
    <Box
      sx={{
        bgcolor: "background.default",
        p: 2,
        borderRadius: 2,
        height: "100%",
      }}
    >
      <Typography variant="h6" gutterBottom>
        <IconComponent
          sx={{
            color: "text.secondary",
            opacity: 0.8,
            fontSize: "1.2rem",
            mt: 0.8,
            mr: 1,
            display: { xs: "none", sm: "initial" },
          }}
        />

        {title}
      </Typography>
      <Typography color="text.secondary" variant="body2">
        {subtitle}
      </Typography>
    </Box>
  );
}