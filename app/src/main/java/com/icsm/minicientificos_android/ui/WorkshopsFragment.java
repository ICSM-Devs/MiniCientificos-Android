package com.icsm.minicientificos_android.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.chip.Chip;
import com.icsm.minicientificos_android.R;
import com.icsm.minicientificos_android.adapters.TallerAdapter;
import com.icsm.minicientificos_android.databinding.FragmentWorkshopsBinding;
import com.icsm.minicientificos_android.models.Taller;
import com.icsm.minicientificos_android.models.TalleresCatalogData;

import java.util.List;

public class WorkshopsFragment extends Fragment implements TallerAdapter.OnTallerClickListener {

    private FragmentWorkshopsBinding binding;
    private TallerAdapter adapter;
    private String filtroCategoriaSeleccionada = "Todas";
    private String textoBusquedaActual = "";

    public WorkshopsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentWorkshopsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Taller> listaTalleres = TalleresCatalogData.getTalleres();

        adapter = new TallerAdapter(listaTalleres, this);
        binding.rvTalleres.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvTalleres.setAdapter(adapter);

        binding.etSearchTalleres.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textoBusquedaActual = s.toString();
                filtrarLista();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        binding.chipGroupTalleres.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.isEmpty()) {
                filtroCategoriaSeleccionada = "Todas";
            } else {
                int selectedId = checkedIds.get(0);
                Chip chip = group.findViewById(selectedId);
                if (chip != null) {
                    filtroCategoriaSeleccionada = chip.getText().toString();
                } else {
                    filtroCategoriaSeleccionada = "Todas";
                }
            }
            filtrarLista();
            actualizarSubtituloDinamico();
        });
        
        actualizarSubtituloDinamico();
    }

    private void filtrarLista() {
        if (adapter != null) {
            adapter.aplicarFiltros(textoBusquedaActual, filtroCategoriaSeleccionada);
            actualizarEstadoVacio();
        }
    }

    private void actualizarEstadoVacio() {
        if (adapter.getItemCount() == 0) {
            binding.tvEmptyTalleres.setVisibility(View.VISIBLE);
            binding.rvTalleres.setVisibility(View.GONE);
        } else {
            binding.tvEmptyTalleres.setVisibility(View.GONE);
            binding.rvTalleres.setVisibility(View.VISIBLE);
        }
    }

    private void actualizarSubtituloDinamico() {
        String base = "Descubre y participa en nuestras experiencias";
        if (!"Todas".equals(filtroCategoriaSeleccionada)) {
            String categoria = filtroCategoriaSeleccionada.toLowerCase();
            if (categoria.equals("presencial") || categoria.equals("virtual") || categoria.equals("híbrido")) {
                binding.tvSubtitleTalleres.setText("Explora todos los talleres en modalidad " + categoria);
            } else {
                binding.tvSubtitleTalleres.setText("Explora todos los talleres de " + categoria);
            }
        } else {
            binding.tvSubtitleTalleres.setText(base);
        }
    }

    @Override
    public void onVerDetalleClick(Taller taller) {
        TallerDetalleBottomSheet bottomSheet = TallerDetalleBottomSheet.newInstance(taller);
        bottomSheet.show(getChildFragmentManager(), "TallerDetalleBottomSheet");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}