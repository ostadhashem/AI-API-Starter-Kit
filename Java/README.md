
# AI API Starter Kit

A Java Spring Boot starter kit for connecting to the OpenAI API. This project demonstrates a basic setup for sending prompts to OpenAI and receiving responses, with support for Dockerization and environment-based configurations.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
    - [Clone the Repository](#clone-the-repository)
    - [Environment Variables](#environment-variables)
    - [Build the Project](#build-the-project)
- [Docker Setup](#docker-setup)
    - [Build and Run the Docker Image](#build-and-run-the-docker-image)
- [Usage](#usage)
- [Testing](#testing)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- **REST API Endpoint**: Send prompts to OpenAI and receive AI-generated responses.
- **Environment-Specific Configuration**: Configurable for `dev` and `prod` environments.
- **Error Handling**: Custom exception handling with detailed error responses.
- **Dockerized**: Easy setup with Docker for consistent deployment.
- **Swagger API Documentation**: Automatically generated, interactive documentation.

## Prerequisites

- **Java 17** or higher
- **Gradle** (optional, as the project includes a Gradle wrapper)
- **OpenAI API Key**: Required to interact with OpenAI API
- **Docker** (optional, for containerization)

## Setup Instructions

### Clone the Repository

```bash
git clone https://github.com/yourusername/ai-api-starter-kit.git
cd ai-api-starter-kit
```

### Environment Variables

Set the `OPENAI_API_KEY` environment variable, as it’s required to authenticate with the OpenAI API.

- **Linux/macOS**:

  ```bash
  export OPENAI_API_KEY=your_openai_api_key
  ```

- **Windows**:

  ```cmd
  setx OPENAI_API_KEY "your_openai_api_key"
  ```

### Build the Project

Use the Gradle wrapper to build the project.

```bash
./gradlew clean build
```

If you encounter permissions issues, ensure `gradlew` is executable:

```bash
chmod +x gradlew
```

## Docker Setup

If you prefer to run the application in a Docker container, follow these instructions.

### Build and Run the Docker Image

1. **Build the Docker image**:

   ```bash
   docker build -t ai-api-starter .
   ```

2. **Run the Docker container**:

   ```bash
   docker run -p 8080:8080 -e OPENAI_API_KEY=your_openai_api_key ai-api-starter
   ```

This will start the application in Docker, accessible at `http://localhost:8080`.

## Usage

The application provides an endpoint to generate AI responses from OpenAI.

- **Endpoint**: `POST /api/openai/generate`
- **Request Body**: JSON containing the prompt text.

Example request:

```json
{
  "prompt": "Explain the benefits of Spring Boot."
}
```

Example `curl` command:

```bash
curl -X POST http://localhost:8080/api/openai/generate -H "Content-Type: application/json" -d "{"prompt": "Explain the benefits of Spring Boot."}"
```

### Expected Response

The server will respond with a JSON object containing the generated text:

```json
{
  "responseText": "Spring Boot simplifies the process of setting up and developing new applications..."
}
```

## Testing

To run tests, use the following command:

```bash
./gradlew test
```

## API Documentation

This project uses SpringDoc to generate Swagger API documentation, accessible at:

```
http://localhost:8080/swagger-ui.html
```

## Contributing

1. Fork the project.
2. Create your feature branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
