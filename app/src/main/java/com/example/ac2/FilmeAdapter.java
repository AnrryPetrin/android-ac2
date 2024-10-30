package com.example.ac2;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FilmeAdapter extends RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder> {

    private List<Filme> filmeList;
    private Context context;

    public FilmeAdapter(Context context, List<Filme> filmeList) {
        this.context = context;
        this.filmeList = filmeList;
    }

    @Override
    public FilmeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_filme, parent, false);
        return new FilmeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmeViewHolder holder, int position) {
        Filme filme = filmeList.get(position);
        holder.textViewTitulo.setText(filme.getTitulo());
        holder.textViewAno.setText(String.valueOf(filme.getAno()));
        holder.textViewDiretor.setText(filme.getDiretor());

        // Obter o ID do recurso da imagem pelo nome
        int resID = context.getResources().getIdentifier(filme.getImagem(), "mipmap", context.getPackageName());
        holder.imageViewPoster.setImageResource(resID);

        holder.buttonSinopse.setOnClickListener(v -> {
            Toast.makeText(context, filme.getSinopse(), Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return filmeList.size();
    }

    public class FilmeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPoster;
        TextView textViewTitulo;
        TextView textViewAno;
        TextView textViewDiretor;
        Button buttonSinopse;

        public FilmeViewHolder(View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            textViewTitulo = itemView.findViewById(R.id.textViewTitulo);
            textViewAno = itemView.findViewById(R.id.textViewAno);
            textViewDiretor = itemView.findViewById(R.id.textViewDiretor);
            buttonSinopse = itemView.findViewById(R.id.buttonSinopse);
        }
    }
}
