package com.icsm.minicientificos_android.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.icsm.minicientificos_android.databinding.FragmentContactoBinding;
import com.icsm.minicientificos_android.utils.WhatsAppHelper;

public class ContactoFragment extends Fragment {

    private FragmentContactoBinding binding;

    public ContactoFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentContactoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupTextChangeListeners();

        binding.btnEnviarContacto.setOnClickListener(v -> validarYEnviarFormulario());
    }

    private void setupTextChangeListeners() {
        binding.etNombre.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.tilNombre.setError(null);
            }
        });

        binding.etEmail.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.tilEmail.setError(null);
            }
        });

        binding.etTelefono.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.tilTelefono.setError(null);
            }
        });

        binding.etAsunto.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.tilAsunto.setError(null);
            }
        });

        binding.etMensaje.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.tilMensaje.setError(null);
            }
        });
    }

    private void validarYEnviarFormulario() {
        boolean esValido = true;

        String nombre = binding.etNombre.getText() != null ? binding.etNombre.getText().toString().trim() : "";
        String email = binding.etEmail.getText() != null ? binding.etEmail.getText().toString().trim() : "";
        String telefono = binding.etTelefono.getText() != null ? binding.etTelefono.getText().toString().trim() : "";
        String asunto = binding.etAsunto.getText() != null ? binding.etAsunto.getText().toString().trim() : "";
        String mensaje = binding.etMensaje.getText() != null ? binding.etMensaje.getText().toString().trim() : "";

        // Validar Nombre
        if (TextUtils.isEmpty(nombre)) {
            binding.tilNombre.setError("Ingresa tu nombre completo");
            esValido = false;
        }

        // Validar Email
        if (TextUtils.isEmpty(email)) {
            binding.tilEmail.setError("Ingresa tu correo electrónico");
            esValido = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.setError("Formato de correo inválido (ejemplo@dominio.com)");
            esValido = false;
        }

        // Validar Teléfono
        if (TextUtils.isEmpty(telefono)) {
            binding.tilTelefono.setError("Ingresa tu número de teléfono / WhatsApp");
            esValido = false;
        } else if (telefono.length() < 7) {
            binding.tilTelefono.setError("Ingresa un número telefónico válido (mín. 7 dígitos)");
            esValido = false;
        }

        // Validar Asunto
        if (TextUtils.isEmpty(asunto)) {
            binding.tilAsunto.setError("Ingresa el asunto del mensaje");
            esValido = false;
        }

        // Validar Mensaje
        if (TextUtils.isEmpty(mensaje)) {
            binding.tilMensaje.setError("Escribe tu mensaje espacial");
            esValido = false;
        }

        if (esValido) {
            WhatsAppHelper.abrirConsultaContacto(requireContext(), nombre, email, telefono, asunto, mensaje);
            
            // Limpiar formulario
            binding.etNombre.setText("");
            binding.etEmail.setText("");
            binding.etTelefono.setText("");
            binding.etAsunto.setText("");
            binding.etMensaje.setText("");

            // Limpiar foco y errores
            binding.getRoot().clearFocus();

            Toast.makeText(requireContext(),
                    "🚀 ¡Abriendo WhatsApp para enviar mensaje!",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(requireContext(),
                    "⚠️ Por favor, corrige los errores señalados en el formulario.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private abstract static class SimpleTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void afterTextChanged(Editable s) {}
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}