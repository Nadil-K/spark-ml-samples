#!/bin/bash

# Open browser
open_browser() {
    url="http://localhost:9090/mendeley/"
    case "$(uname)" in
        Darwin*)
            open "$url"
            ;;
        Linux*)
            xdg-open "$url"
            ;;
        MINGW*|MSYS*|CYGWIN*)
            start "" "$url"
            ;;
        *)
            echo "Please open $url manually. Unsupported OS: $(uname)"
            ;;
    esac
}

(
    while true; do
        if curl --silent --head http://localhost:9090/mendeley/ | grep "200 OK" > /dev/null; then
            open_browser
            break
        fi
        sleep 1
    done
) &

# Run the Java app
java -jar api/build/libs/api-1.0-SNAPSHOT.jar
