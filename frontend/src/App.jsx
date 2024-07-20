import './App.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import NotFoundPage from "./core/system/NotFoundPage.jsx";
import { CssBaseline, ThemeProvider} from "@mui/material";
import { lightTheme } from "./assets/themes/Theme.js";
import LandingPage from "./feature/landing/LandingPage.jsx";
import CenterHub from './feature/dashboard/CenterHub.jsx';


function App() {

return (

<div className="App">

  <CssBaseline/>
  <ThemeProvider theme={lightTheme} >
  <Router>
    <Routes>
      <Route path="*" element={<NotFoundPage />} />
      <Route path="/" element={<LandingPage />} />
      <Route path="/center" element={<CenterHub />} />
    </Routes>
  </Router>
  </ThemeProvider>

</div>
)
}

export default App