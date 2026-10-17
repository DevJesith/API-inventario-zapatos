    package com.practice.inventariozapatos.exception;

    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.ErrorResponse;
    import org.springframework.web.bind.MethodArgumentNotValidException;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.RestControllerAdvice;
    import org.springframework.web.servlet.View;
    import java.time.LocalDateTime;
    import java.util.stream.Collectors;
    
    @RestControllerAdvice
    public class GlobalExceptionHandler {
    
        // Captura errores de argumentos invalidos (ej. ID negativo, datos mal formados)
        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    ex.getMessage(),
                    LocalDateTime.now()
            );
    
            return ResponseEntity.badRequest().body(errorResponse);
        }
    
        // Captura cualquier excepcion generica no controlada (ultima red de seguridad — 500)
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handlerGeneral(Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage(),
                    LocalDateTime.now()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    
        // Captura cuando un recurso solicitado no existe (ej. producto no encontrado — 404)
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.NOT_FOUND.value(),
                    ex.getMessage(),
                    LocalDateTime.now()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        // Captura errores de validacion con @Valid en DTOs (ej. campos obligatorios vacios — 400)
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
            String errorMessage = ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(","));
    
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    errorMessage,
                    LocalDateTime.now()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        // Captura intentos de acceso sin permisos suficientes (ej. rol no autorizado — 403)
        @ExceptionHandler(PermissionDeniedException.class)
        public ResponseEntity<ErrorResponse> handlerAccessDenied(PermissionDeniedException ex){
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.FORBIDDEN.value(),
                    ex.getMessage(),
                    LocalDateTime.now()
            );
    
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }

        // Captura conflictos de negocio (ej. duplicados, estado inconsistente — 409)
        @ExceptionHandler(ConflictException.class)
        public ResponseEntity<ErrorResponse> handleConflic(ConflictException ex){
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.CONFLICT.value(),
                    ex.getMessage(),
                    LocalDateTime.now()
            );
    
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }

        // Captura validaciones de reglas de negocio (ej. stock insuficiente, precio invalido — 422)
        @ExceptionHandler(BusinessValidationException.class)
        public ResponseEntity<ErrorResponse> handleBusinessValidation(BusinessValidationException ex){
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.UNPROCESSABLE_ENTITY.value(),
                    ex.getMessage(),
                    LocalDateTime.now()
            );
    
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
        }
    }
