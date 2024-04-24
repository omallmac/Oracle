<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buscar y Mostrar Pokémon</title>
</head>
<body>
    <center>
        <h1>Buscar y Mostrar Pokémon</h1>
        <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="POST">
            <label for="pokemonName">Nombre del Pokémon:</label>
            <input type="text" name="pokemonName" id="pokemonName" value="<?php if(isset($_POST['pokemonName'])) echo htmlspecialchars($_POST['pokemonName']); ?>">
            <input type="submit" value="Buscar">
        </form>
        <?php
        // Verificar si se realizó una búsqueda y si se encontró la imagen del Pokémon
        if(isset($_POST['pokemonName'])) {
            $pokemonName = $_POST['pokemonName'];
            $pokemonName = strtolower($pokemonName); // Convertir el nombre del Pokémon a minúsculas

            // Obtener datos del Pokémon desde la PokeAPI
            $pokemonData = @file_get_contents("https://pokeapi.co/api/v2/pokemon/{$pokemonName}");
            
            // Verificar si se obtuvieron datos del Pokémon
            if($pokemonData !== false) {
                $pokemonData = json_decode($pokemonData, true);

                // Verificar si se encontró la URL de la imagen del Pokémon
                if(isset($pokemonData['sprites']['other']['official-artwork']['front_default'])) {
                    // Obtener la URL de la imagen del Pokémon
                    $pokemonImage = $pokemonData['sprites']['other']['official-artwork']['front_default'];
                    ?>
                    <img src="<?php echo $pokemonImage; ?>" alt="Imagen del Pokémon">
                    <?php
                            } else {
                                // Mostrar la imagen de Pikachu si no se encontró la imagen del Pokémon
                    ?>
                    <img style="width: 30%; " src="imagenes/pikachu-intro.png" alt="pokemon">
                    <h2 style="color: red ;">Digite el nombre del pokemon a buscar</h2>
                    <?php
                            }
                        } else {
                            // Mostrar la imagen de Pikachu y el mensaje de error si no se ingresó ningún nombre de Pokémon
                    ?>
                    <img style="width: 30%; " src="imagenes/pikachu-intro.png" alt="pokemon">
                    <h2 style="color: red ;">Nombre de Pokemon no encontrado</h2>
                    <?php
                        }
                    }
                    ?>
                </center>
            </body>
            </html>
