package br.dev.gustavofernandes.tarefas.adapter;

import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.dev.gustavofernandes.tarefas.R;
import br.dev.gustavofernandes.tarefas.model.Tarefa;

public class TarefasAdapter extends RecyclerView.Adapter<TarefasAdapter.TarefaViewHolder> {

    private List<Tarefa> lstTarefa = new ArrayList<>();

    public TarefasAdapter(List<Tarefa> lstTarefa1) {
        lstTarefa = lstTarefa1;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View sView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tarefa_item,parent,false);
        return new TarefaViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        Tarefa tarefa = lstTarefa.get(position);
        holder.edtTarefa.setText(tarefa.getDescricao());
    }

    @Override
    public int getItemCount() {
        return lstTarefa.size();
    }

    public class TarefaViewHolder extends RecyclerView.ViewHolder {

        TextView edtTarefa;
        public TarefaViewHolder(@NonNull View itemView) {
            super(itemView);
            edtTarefa = itemView.findViewById(R.id.edt_tarefa);
        }
    }
}
